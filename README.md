# Cuac-Covid University Alert Center
(_Original Repo:https://github.com/soly-02/Cuac_)
### _Εργασία φοιτητών Εφαρμοσμένης Πληροφορικής στα πλαίσια του μαθήματος "Τεχνολογία Λογισμικού"_


Το CUAC έχει σκοπό την διευκόλυνση της διαχείρισης της πανδημίας στο χώρο του πανεπιστημίου. Εφόσον δεν έχει υπάρξει ακόμα κάποια εφαρμογή η οποία κάνει άμεσο τον εντοπισμό κρούσματος, το πρόγραμμα αναπτύχθηκε για να γίνει αυτή η διαδικασία πιο εύκολη, προσβάσιμη και διαδραστική στους φοιτητές

### Περιεχόμενα
- Παροχές της εφαρμογής
- Εργαλεία για την ανάπτυξη του λογισμικού
- Εγκατάσταση
- Ορισμένες ατέλειες


## Παροχές της εφαρμογής

1. Χρήση διαπιστευτηρίων, που αποτελούνται από το email και τον κωδικό που
θα επιλέγει ο χρήστης. Εγγραφή, σύνδεση και αποθήκευση ατομικών δεδομένων.

2. Δήλωση θέσης και ώρα παρακολούθησης διάλεξης. Έγκαιρη πληροφόρηση σε 
περίπτωση διαπίστωσης κρούσματος
3. Παροχή οθόνης στην οποία θα υπάρχει η δυνατότητα να ανεβάζει, να προβάλει και να αποθηκεύει ο χρήστης το πιστοποιητικό ή το rapid τεστ του.

4. Δήλωση θετικού τεστ/νόσησης και αποστολή ειδοποίησης στους υπόλοιπους χρήστες της εφαρμογής.

5. Countdowns/ αντίστροφη μέτρηση καραντίνας ή λήξης πιστοποιητικού.
6. Αποθήκευση όλων των δεδομένων και ανάκτηση τους κάθε φορά που ξανασυνδέεται ο χρήστης.


> Πλαίσιο εφαρμογής του λογισμικού μας θα αποτελέσει ο χώρος του 
πανεπιστημίου από την πλευρά του φοιτητών, με σκοπό την ευκολότερη και 
αμεσότερη καταγραφή κρουσμάτων. Ένα από τα επιτεύγματα της εφαρμογής 
θα είναι η αποσυμφόρηση των υπέρογκων εργασιών της γραμματείας και των 
καθηγητών.


### Εργαλεία για την ανάπτυξη του λογισμικού
Χρησιμοποιήθηκαν αρκετά μέσα για να καταλήξουμε στην τελική μορφή του προγράμματος, πιο συγκεκριμένα:

- Eclipse IDE - το περιβάλλον όπου γράφτηκε ο κώδικας.
- Okeanos - Υπηρεσία cloud με απομακρυσμένη μηχανή Windows για να τρέχει διαρκώς η βάση δεδομένων.
- MySQL - Βάση δεδομένων της εφαρμογής μας.
- Visual Studio Code - για την δημιουργία ορισμένων Python script που αλληλεπιδρούν με την βάση.

Και οι ενδεικτικές βιβλιοθήκες: 
- java.awt
- javax.swing
- jpedal
- java.time
- java.text
- mysql-connector
- jgoodies-forms


## Εγκατάσταση
1. Κατεβάζετε το αρχείο "Cuac1.0.zip" από τα releases.
2. Κάνετε αποσυμπίεση το αρχείο στον επιθυμητό κατάλογο στον υπολογιστή σας.
3. Τρέξτε το αρχείο "Couac1.0.jar"
4. Καλώς ήρθατε στην εφαρμογή μας!

_(demo credentials: dummy@gmail.com/Passw0rd)_

Αν προτιμάτε την εκτέλεση κατευθείαν από τον πηγαίο κώδικα θα ήταν προτιμότερο να είσαγετε ολόκληρο το repository ως ένα [Eclipse](https://www.eclipse.org/downloads/) project.
1. `git clone https://github.com/soly-02/Cuac`
2. Εφόσον ανοίξετε το Eclipse εισάγετε τον φακελο Cuac  (_File->Import->General->Existing Project into Workspace_)
3. Στο Main.java _δεξί κλικ->Run as Java Application_.

Σε περίπτωση που τρέχετε την εφαρμογή μετά τις **30/6/2022**  θα πρέπει δυστυχώς να χρησιμοποιήσετε τοπικά μια βάση δεδομένων mySQL.
1. Κατεβάστε την mySQL  από [εδώ](https://dev.mysql.com/downloads/installer/)
2. Ανοίξτε το mySQL Workbench και συνδεθείτε στον τοπικό server
3. Εισάγετε στον server το αρχείο [cuacDB.sql](https://github.com/soly-02/Cuac/blob/master/database/cuacDB.sql) (Server->Data Import και έπειτα τσεκάρετε _Import from Self-Contained file_ επιλέγοντας το παραπάνω αρχείο)
4. Τέλος, ανοίξτε το [db-credentials.config](https://github.com/soly-02/Cuac/blob/master/database/db-credentials.config) και βάλτε τα στοιχεία του τοπικού server
5. Επιτέλους μπορείτε να τρέξετε την εφαρμογή


## Ατέλειες/Μελλοντικές βελτιώσεις
 1. Το λογισμικό μας, κάνει χρήση μιας βιβλιοθήκης(JPedal) με free trial για 15 μέρες, οπότε ο κώδικας χρειάζεται ανανέωση όταν λήγει. Αφορά την λειτουργία (3), δηλαδή την προβολή του πιστοποιητικού νόσησης, εμβολίου ή rapid test (PDF viewer). Μια λύση θα ήταν η αγορά αυτής της βιβλιοθήκης.
 2. Η βάση δεδομένων που χρησιμοποιήθηκε ήταν αποθηκευμένη σε μια Windows μηχανή στο Okeanos, για να είναι πάντα προσβάσιμη, η οποία μετά τις 30 Ιουνίου θα κλείσει. Η λύση αυτού του ζητήματος θα ήταν η μεταφορά της βάσης σε άλλο περιβάλλον.
 3. Για μεγαλύτερη ευκολία θα μπορούσε το Covid Wallet να αναγνωρίζει αυτόματα τα στοιχεία και το είδος του πιστοποιητικού όμως για να γίνει αυτό θα πρέπει να υπάρξει και το κατάλληλο API από το gov.gr
 4. Βελτίωση του GUI
 5. Αποθήκευση των κωδικών πρόσβασης σε κρυπτογραφμένη μορφή με χρήση salt για μεγαλύτερη ασφάλεια

# Σας ευχαριστούμε που προτιμήσατε το λογισμικό μας.
