package repositories;


import models.BankAccount;

import java.util.List;

public interface BankAccountRepository extends BillingDetailsRepository<BankAccount, Long> {
    List<BankAccount> findBySwift(String swift);
}
