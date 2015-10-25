# Android-SMS-Gateway
-----------------------------
*Testing SMS based applications using simple SMS Gateway built using Android. This module was written by for testing our SMS based search algorithm as final year project*


What this little code does?
=========================================

if you want to test your SMS based app or anything which requires your app to send and receive text SMS / HTTPtoSMS or SMStoHTTP you can use this code to quicky test your business logic without worrying about a real SMS gateway.

This code turns your android phone into SMS gateway.

How it works?
========================================
* Receives SMS text
* Forwards the the text to specified API URL vi HTTP POST
* Receive response, process and send it back to cell number form which SMS was received.