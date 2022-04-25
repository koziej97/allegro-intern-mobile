# Allegro Summer e-Xperience 2022 - Mobile Software Engineer Intern

Mój adres e-mail użyty w procesie rekrutacji: <lukasz.koziej97@gmail.com>

Projekt wykonany na potrzeby zadania rekrutacyjnego na stanowisko Intern - Mobile Software Engineer dla Allegro. Wykonałem go w całości w Kotlinie za pomocą IDE Android Studio.

## Uruchomienie aplikacji:
Aby uruchomić projekt należy pobrać kod z repozytorium, a następnie zaimportować projekt do Android Studio, gdzie przy pomocy emulatora AVD (lub podłączonego smartphone z Androidem w trybie debbugowania) można uruchomić aplikację.

## Uproszczenia przyjęte w programie:
- program pobiera listę maksymalnie 30 repozytoriów użytkownika - spowodowane jest to faktem, że API Githuba dla polecenia *GET repos* zwraca jedynie pierwszą stronę z wynikami wszystkich repozytoriów (gdzie na jednej stronie znajduje się właśnie 30 repozytoriów)
- lista użytych w repozytorium języków programowania została wyświetlona w postaci sformatowanego Stringa

## Komentarz do wykonanego projektu:
Do wykonywania zapytań REST Http posłużyłem się biblioteką Retrofit. Parsując JSONa zawierającego listę repozytoriów posłużyłem się biblioteką Moshi, która konwertuje JSONa na data klasę Kotlina. Następnie pobierałem z niej tylko interesującą mnie informację - nazwę repozytorium. W przypadku parsowania JSONa z użytymi w danym repozytorium językami programowania problem był trudniejszy, ponieważ nie otrzymywaliśmy za każdym razem takiej samej struktury *klucz:wartość* jak w przypadku JSONa z repozytoriami - dla języków programowania nazwy kluczy różniły się w zależności od tego, jaki język się pojawiał, przez co nie dało się w łatwy sposób użyć konwertera Moshi. Z tego powodu do sparsowania drugiego JSONa użyłem biblioteki konwertującej JSONa do zwykłego Stringa, a następnie zmapowałem uzyskanego Stringa za pomocą GSONa, aby mieć łatwiejszy dostęp do jego danych.

Projekt wykonałem korzystając z Fragmentów i XML'a - można by było użyć nowego sposobu tworzenia aplikacji natywnie na Androida - Jetpack Compose. Zdecydowanie ułatwiłoby to zadanie ze względu na zastąpienie RecyclerView oraz potrzebnych do niego adapterów i handlerów za pomocą prostej funkcji *Composable LazyRow*. Jeżeli byłaby taka potrzeba mogę wykonać ten sam projekt również z użyciem Jetpack Compose. Wykonując to zadanie uznałem, że bardziej wartościowe będzie pokazanie, że potrafię sobie poradzić z takimi zagadnieniami jak np. RecyclerView, niż wybrać prostszą drogę.

## Dalsze iteracje projektu:
- poprawienie wizualnego aspektu aplikacji - w tym momencie jest to działająca aplikacja z podstawową szatą graficzną
- pobieranie wszystkich repozytoriów użytkownika - opracowanie zapytania, które uwzględniałoby wiele stron wyników
- dodanie RecyclerView dla listy użytych języków programowania w danym repozytorium
