#!/bin/sh

printf "Which day do you want to start? "
read answer

NEW_DIR="day${answer}"
mkdir "${NEW_DIR}"

echo "Creating files need in ${NEW_DIR}"
TEMPLATE_DIR="day0X"
cp "${TEMPLATE_DIR}/DayX.kt" "${NEW_DIR}/Day${answer}.kt"
cp "${TEMPLATE_DIR}/Day0X.txt" "${NEW_DIR}/Day${answer}.txt"
cp "${TEMPLATE_DIR}/Day0X_test.txt" "${NEW_DIR}/Day${answer}_test.txt"

sed -i '' "s/0X/${answer}/g" "${NEW_DIR}/Day${answer}.kt"

echo "You are good to go! The following has been created! Go Code!"

ls -l "${NEW_DIR}"