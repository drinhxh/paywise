import { BankAccount } from "./bank_account";
import { Card } from "./card";
import { FundTransfer } from "./fund_transfer";
import { Roles } from "./roles";

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  password: string;
  bankAccount: BankAccount;
  card: Card;
  senderFundTransfers: FundTransfer[];
  receiverFundTransfers: FundTransfer[];
  roles: Roles[];
}