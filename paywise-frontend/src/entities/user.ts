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
  bankAccount: BankAccount; // Add the BankAccount type
  card: Card; // Add the Card type
  senderFundTransfers: FundTransfer[]; // Add the FundTransfer array type
  receiverFundTransfers: FundTransfer[]; // Add the FundTransfer array type
  roles: Roles[]; // Add the Roles array type
}