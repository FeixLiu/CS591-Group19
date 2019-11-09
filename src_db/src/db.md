#bank
saveInterest double<br>
loanInterest double<br>
serviceFee double<br>
logId String<br>
id String<br>
dollarBalance double<br>
euroBalance double<br>
yuanBalance double<br>
managerPass String<br>
managerName String<br>
date Int<br>
savingLimit real<br>
securityLimit real<br>

#user
id String prim-key<br>
name String<br>
pass String<br>
logId String<br>

#account
id String prim-key<br>
userId String foreign-key<br>
pass String<br>
type String<br>

#loan
id String prim-key<br>
userId string foreign-key<br>
balance double<br>

#balance
id String prim-key<br>
accountId String foreign-key<br>
type String<br>
money double<br>

#security
id int prim-key<br>
securityAccountId String<br>
pasls String<br>
companyName String <br>
price double<br>
share int<br>
accountId String<br>

#stock
companyName String prim-key<br>
price double<br>
available int<br>
haveSold int<br>
curSold int<br>
historyPrice String<br>

#log
logId String prim-key<br>
log String<br>
date String prim-key<br>
