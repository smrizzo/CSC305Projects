#!/bin/bash
cd `dirname $0`
if [ "$MATERIALS_DIR_305" = "" ] ; then
    echo "Please set MATERIALS_DIR_305"
    exit 1
fi
LIBS=$MATERIALS_DIR_305/lib/testy.jar

#
# Compile the program:
#
rm -rf out
mkdir -p out
JAVA_SRC=`find src -name '*.java' -print`
javac -cp out:out/kotlin.jar:$LIBS -d out -Xlint:unchecked -Xmaxerrs 5  \
    -sourcepath src $JAVA_SRC
if [ $? != 0 ] ; then
    exit 1
fi

java -ea -cp $LIBS:out Main "$@"
