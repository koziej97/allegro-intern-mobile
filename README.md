# Allegro Summer e-Xperience 2022 - Mobile Software Engineer Intern

Projekt wykonany na potrzeby zadania rekrutacyjnego na stanowisko Intern - Mobile Software Engineer dla Allegro. Wykonałem go w całości w Kotlinie za pomocą IDE Android Studio.

## Cel zadania:
Stwórz aplikację wyświetlającą listę języków programowania wykorzystanych w repozytoriach danego użytkownika GitHub. Aplikacja powinna zawierać ekran listy repozytoriów oraz osobny ekran, w którym wyświetlone zostaną szczegóły wybranego repozytorium, tj. informacja jakie języki są wykorzystane oraz liczba bajtów kodu napisanego w każdym z tych języków.

## Demo:
![](demo.gif)

## Uruchomienie aplikacji:
Aby uruchomić projekt należy pobrać kod z repozytorium, a następnie zaimportować projekt do Android Studio, gdzie przy pomocy emulatora AVD (lub podłączonego smartphone z Androidem w trybie debbugowania) można uruchomić aplikację.

## Wykorzystane biblioteki:
- Retrofit
- Moshi
- Gson

## Komentarz do wykonanego projektu:
- Do wyświetlania listy repozytoriów wykorzystałem RecyclerView wraz ze Scroll Listenerem, dzięki czemu gdy użytkownik zescrolluje na sam dół listy repozytoriów następuje pobranie kolejnej listy danych
- Do wykonywania zapytań REST Http posłużyłem się biblioteką Retrofit. 
- Parsując JSONa zawierającego listę repozytoriów posłużyłem się biblioteką Moshi, która konwertuje JSONa na data klasę Kotlina. Następnie pobierałem z niej tylko interesującą mnie informację - nazwę repozytorium. W przypadku parsowania JSONa z użytymi w danym repozytorium językami programowania problem był trudniejszy, ponieważ nie otrzymywaliśmy za każdym razem takiej samej struktury *klucz:wartość* jak w przypadku JSONa z repozytoriami - dla języków programowania nazwy kluczy różniły się w zależności od tego, jaki język się pojawiał, przez co nie dało się w łatwy sposób użyć konwertera Moshi. Z tego powodu do sparsowania drugiego JSONa użyłem biblioteki konwertującej JSONa do zwykłego Stringa, a następnie zmapowałem uzyskanego Stringa za pomocą GSONa, aby mieć łatwiejszy dostęp do jego danych.
