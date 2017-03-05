**Aihe:** Yksinkertainen reaaliaikainen ja kaksiulotteinen laivapeli,
jossa ideana on saada laivaan lastatut laatikot kuljetettua aallokon yli satamaan.
Pelin pohjaksi tehdään yksinkertainen fysiikkamoottori, joka huolehtii aallokon, veneen ja laatikoiden fysiikasta.

*Update:* Laatikkoidea jäi toteuttamatta ja lopputuloksena on vain pelin runko joka toimii muuten täysin.

**Ohjelman rakenne:** Pelin ydin ajetaan käyttöliittymäluokasta vaikka pelin käyttöliittymän ja logiikan pitäisi olla muuten täysin riippumattomat (en tosin ole testannut sitä). Peli ajetaan main-metodin sisällä olevassa while-loopissa josta päivitetään vuoron perään pelin ydin ja peli-ikkunaan piirtäminen. Peli-ikkuna lähettää myös näppäinkomennot ytimelle. Ydin kutsuu Game-luokassa koottujen peli-objektien update-metodeita niin usein kuin mahdollista ja peli-objektit ajoittavat muutokset kello-luokan avulla. Tämä mahdollistaa sen että peli toimii teoriassa tasaisesti isäntäkoneen laskentatehosta riippumatta. Peli-ikkuan asettaa Input-luokalle näppäinpainalluksia kuvaavat arvot jotka tässä tapauksessa Ship-luokka lukee. Vector2-luokkaa käytetään sijaintien tallettamiseen.

Piirtäminen tapahtuu siten, että käyttöliittymä pyytää kameraa piirtämään kaikki peliobjektit, ja kamera pyytää vastaavasti peliobjekteja piirtämään itseensä. Peliobjektit piirtävät itsensä kameraan joka piirtää viewporttia vastaavan näkymän peli-ikkunaan.

Ääni ja kuvat ladataan Resources-luokan avulla. Peliobjektit käyttävät kuvia suoraan luokasta, mutta äänet toistetaan erillistä Sound-luokkaa hyödyntämällä. 


**Konseptitaidetta**
![alt Konseptitaidetta](http://files.1337upload.net/Laivapeli-263375.png)

**Luokkakaavio**
![alt Luokkakaavio](luokkakaavio.png)

**Sekvenssikaavio**
Kaavio kuvaa pelimoottorin rakennetta
![alt Sekvenssikaavio](sekvenssikaavio.png)

**Sekvenssikaavio**
Kaavio kuvaa käyttäjän syötteenkäsittelyä
![alt Sekvenssikaavio](sekvenssikaavio.png)