set LIBS=%$MATERIALS_DIR_305%/lib/testy.jar;%MATERIALS_DIR_305%/lib/spritely.jar

erase /s /q out
mkdir out
javac -Xlint:unchecked -Xmaxerrs 5 -sourcepath src  -cp %LIBS% -d out src\*.java 
java -cp %LIBS%;out -ea Main %1 %2 %3 %4 %5 %6 %7 %8 %9
