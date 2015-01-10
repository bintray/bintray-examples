#!/bin/bash

#Sample Usage: pushToBintray.sh username api_key repo bash_example

API=https://api.bintray.com
RPM=yuma-devel-2.2-4.noarch.rpm
PACKAGE_DESCRIPTOR=bintray-package.json

BINTRAY_USER=$1
BINTRAY_API_KEY=$2
BINTRAY_REPO=$3
PCK_NAME=$4
PCK_VERSION=$(rpm -qp ${RPM} --qf "%{VERSION}")

main() {
  CURL="curl -u${BINTRAY_USER}:${BINTRAY_API_KEY} -H Content-Type:application/json -H Accept:application/json"
  if (check_package_exists); then
    echo "The package ${PCK_NAME} does not exit. It will be created"
    create_package
  fi

  deploy_rpm
}

check_package_exists() {
  echo "Checking if package ${PCK_NAME} exists..."
  package_exists=`[ $(${CURL} --write-out %{http_code} --silent --output /dev/null -X GET  ${API}/packages/${BINTRAY_USER}/${BINTRAY_REPO}/${PCK_NAME})  -eq 200 ]`
  echo "Package ${PCK_NAME} exists? y:1/N:0 ${package_exists}"
  return ${package_exists}
}

create_package() {
  echo "Creating package ${PCK_NAME}..."
  if [ -f "${PACKAGE_DESCRIPTOR}" ]; then
    data="@${PACKAGE_DESCRIPTOR}"
  else
    data="{
    \"name\": \"${PCK_NAME}\",
    \"desc\": \"auto\",
    \"desc_url\": \"auto\",
    \"labels\": [\"bash\", \"example\"]
    }"
  fi

  ${CURL} -X POST -d "${data}" ${API}/packages/${BINTRAY_USER}/${BINTRAY_REPO}
}

deploy_rpm() {
  if (upload_content); then
    echo "Publishing ${RPM}..."
    ${CURL} -X POST ${API}/content/${BINTRAY_USER}/${BINTRAY_REPO}/${PCK_NAME}/${PCK_VERSION}/publish -d "{ \"discard\": \"false\" }"
  else
    echo "[SEVERE] First you should upload your rpm ${RPM}"
  fi
}

upload_content() {
  echo "Uploading ${RPM}..."
  uploaded=` [ $(${CURL} --write-out %{http_code} --silent --output /dev/null -T ${RPM} -H X-Bintray-Package:${PCK_NAME} -H X-Bintray-Version:${PCK_VERSION} ${API}/content/${BINTRAY_USER}/${BINTRAY_REPO}/${RPM}) -eq 201 ] `
  echo "RPM ${RPM} uploaded? y:1/N:0 ${uploaded}"
  return ${uploaded}
}

main "$@"
