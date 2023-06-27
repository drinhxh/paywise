import { User } from "./user";

export interface FundTransfer {
  id: number;
  amount: number;
  sourceAccountNumber: string;
  destinationAccountNumber: string;
  dateTime: string;
  user_sender: User; // Add the User type
  user_receiver: User; // Add the User type
}

