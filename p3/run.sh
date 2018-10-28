#!/bin/bash
cd `dirname $0`
if [ "$MATERIALS_DIR_305" = "" ] ; then
    # You can set MATERIALS_DIR_305 in you .mybashrc or
    # .bashrc or wherever if you don't want to have to edit
    # this script when you copy the directory.
    MATERIALS_DIR_305=../../materials
fi
LIBS=$MATERIALS_DIR_305/lib/testy.jar:$MATERIALS_DIR_305/lib/spritely.jar

#
# Compile the program:
#
rm -rf out
mkdir out
javac -Xlint:unchecked -Xmaxerrs 5 -sourcepath src  \
	-cp $LIBS -d out `find src -name *.java -print`
if [ $? != 0 ] ; then
    exit 1
fi

#
# If ^C is entered, make sure we get out of "stty cbreak" mode,
# as set below:
#
function reset_tty {
    stty sane
}
trap reset_tty INT
HEADLESS=""


# To test headless, set the java system property, and
# put the terminal in character mode:
# stty -echo cbreak; HEADLESS=-Djava.awt.headless=true

java $HEADLESS -cp $LIBS:out -ea Main $*
reset_tty
