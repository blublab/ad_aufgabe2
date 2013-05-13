**** Readme zum starten des ausgeglichenem 2 Wege Mergesort ****

In dem Package aufgabe2.data befindet sich die Main.java. Die Klasse
Main enth�lt eine Methode die den Startupcode, das erstellen einer
unsortierten Datei mit 1.000.000 Zahlen und dem Verweis auf die Klasse
aufgabe2.data.Constants in der es m�glich ist ein paar Parameter f�r den
2 Wege Mergesort einzustellen. Um mit den aktuellen Einstellungen einen
Durchlauf zu starten, muss der Java VM Parameter -Xmx2200m beim 
ausf�hren mit gegeben werden,andernfalls kommt es zu einer
OutOfMemoryException.

Mit dem Parameter Constants.BUFFERSIZE_APPLICATION wird der verwendete
RAM der Anwendung festgelegt.

Die Parameter Main.integerProSchreibvorgang und Main.anzahlSchreibvorgaenge
dienen zur Erstellung einer unsortierten Datei der g��e integerProSchreibvorgang * anzahlSchreibvorgaenge

In dem Package aufgabe2.algorithm befindet sich in der Klasse ExternerMergeSort
die Methode sort die logik des 2 Wege Mergesorts.

In dem Package aufgabe2.algorithm.parallel befinden sich die MultiThreaded implementation
unseres Quicksortes






