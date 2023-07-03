import { Component, OnInit } from '@angular/core';
import { User } from 'src/entities/user';
import { UserService } from '../user.service';
import { FundTransfer } from 'src/entities/fund_transfer';
import { Observable, forkJoin, map, of, switchMap } from 'rxjs';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css', './user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  public user: User | undefined;
  public senderFundTransfers: FundTransfer[] = [];
  public receiverFundTransfers: FundTransfer[] = [];
  public senderUsers: User[] = [];
  public receiverUsers: User[] = [];

  // not duplicated SENDER-USERS
  public removedDuplicateSenderUsers: User[] = [];

  // for user index increment 
  public currentIndex: number = 0;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe((user: User | undefined) => {
      this.user = user;
      if (user) {
        this.getSenderFundTransfers(user.id);
        this.getReceiverFundTransfers(user.id);
      }
    });
  }

  // for removing duplicated on SENDER-USERS
  getRemovedDuplicateSenderUsers(): void {
    this.removedDuplicateSenderUsers = [];
    this.senderUsers.forEach(user => {
      if (!this.removedDuplicateSenderUsers.some(u => u.id === user.id)) {
        this.removedDuplicateSenderUsers.push(user);
      }
    });
  }

  // for user index increment
  incrementIndex(){
    this.currentIndex++;
  }

  getSenderFundTransfers(userId: number): void {
    this.userService.getSenderFundTransfers(userId).subscribe(
      (fundTransfers: FundTransfer[]) => {
        this.senderFundTransfers = fundTransfers;
        this.getSenderUsers();
      },
      (error) => {
        console.log('Error retrieving sender fund transfers:', error);
      }
    );
  }

  getReceiverFundTransfers(userId: number): void {
    this.userService.getReceiverFundTransfers(userId).subscribe(
      (fundTransfers: FundTransfer[]) => {
        this.receiverFundTransfers = fundTransfers;
        this.getReceiverUsers();
      },
      (error) => {
        console.log('Error retrieving receiver fund transfers:', error);
      }
    );
  }

  getSenderUsers(): void {
    for (let i = 0; i < this.senderFundTransfers.length; i++) {
      const ft = this.senderFundTransfers[i];
  
      this.userService.getUserByBankAccount(ft.destinationAccountNumber).subscribe(
        (tempUser: User) => {
          this.senderUsers.push(tempUser);
        },
        (error) => {
          console.log('Error retrieving sender user:', error);
        }
      );
    }
  }

  getReceiverUsers(): void {
    for (let i = 0; i < this.receiverFundTransfers.length; i++) {
      const ft = this.receiverFundTransfers[i];
  
      this.userService.getUserByBankAccount(ft.sourceAccountNumber).subscribe(
        (tempUser: User) => {
          this.receiverUsers.push(tempUser);
        },
        (error) => {
          console.log('Error retrieving sender user:', error);
        }
      );
    }
  }
}



