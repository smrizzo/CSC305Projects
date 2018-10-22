#!/bin/bash
cd `dirname $0`
if [ "$MATERIALS_DIR_305" = "" ] ; then
    MATERIALS_DIR_305=../../materials
fi
LIBS=$MATERIALS_DIR_305/lib/testy.jar:$MATERIALS_DIR_305/lib/spritely.jar

#
# Compile the program:
#
rm -rf out
mkdir out
javac -Xlint:unchecked -Werror -Xmaxerrs 5 -sourcepath src  \
	-cp $LIBS -d out `find src -name "*.java" -print`
if [ $? != 0 ] ; then
    exit 1
fi

java -cp $LIBS:out -ea Main "$@"
