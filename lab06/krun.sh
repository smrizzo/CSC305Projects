#!/bin/bash
cd `dirname $0`
if [ "$MATERIALS_DIR_305" = "" ] ; then
    # You can set MATERIALS_DIR_305 in you .mybashrc or
    # .bashrc or wherever if you don't want to have to edit
    # this script when you copy the directory.
    MATERIALS_DIR_305=../../materials
fi
LIBS=$MATERIALS_DIR_305/lib/testy.jar

#
# Compile the program:
#
rm -rf out
mkdir out
JAVA_SRC=`find src -name '*.java' -print`
kotlinc -cp $LIBS -d out/kotlin.jar -include-runtime  \
	`find src -name *.kt -print`  $JAVA_SRC
if [ $? != 0 ] ; then
    exit 1
fi
if [ "$JAVA_SRC" != "" ] ; then
    javac -cp out:out/kotlin.jar:$LIBS -d out -Xlint:unchecked -Xmaxerrs 5  \
        -sourcepath src $JAVA_SRC
    if [ $? != 0 ] ; then
	exit 1
    fi
fi


java -ea -cp $LIBS:out:out/kotlin.jar Main "$@"
