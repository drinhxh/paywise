import { Component, OnInit, importProvidersFrom } from '@angular/core';
import { User } from 'src/entities/user';
import { UserService } from '../user.service';
import { FundTransfer } from 'src/entities/fund_transfer';
import { Observable, forkJoin, map, of, switchMap } from 'rxjs';
import { ViewChild, ElementRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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

  // For Money-Transfer
  public receiverUsername: string | undefined;
  public transferAmount: number | undefined;

  constructor(private userService: UserService, private http: HttpClient) { }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe((user: User | undefined) => {
      this.user = user;
      if (user) {
        this.getSenderFundTransfers(user.id!);
        this.getReceiverFundTransfers(user.id!);
        // this.userService.transferMoney(1, "fake thug", 200);
      }
    });
  }

  

  // MODAL MONEY TRANSFER
  public openModal() {
    const modal = document.getElementById("moneyTransferModal");
    if (modal) {
      modal.style.display = "block";
    }

    const cancelButton = document.getElementById("cancel");
    cancelButton?.addEventListener("click", function() {
    if(modal){
      modal.style.display = "none";
    }
    });
    
    const sendButton = document.getElementById("send");
    sendButton?.addEventListener("click", () => {
      if (this.user && this.receiverUsername && this.transferAmount) {
        // this.transferMoney(1, "baba", 100);
        this.userService.transferMoney(this.user.id!, this.receiverUsername, this.transferAmount)
          .subscribe(
            (response) => {
              alert("Transfer succesful!");
              // Transfer successful, handle the response if needed
              console.log("Transfer successful:", response);
            },
            (error) => {
              // Handle transfer error
              alert("[ERROR] Transfering money!!");
              console.log("Error transferring money:", error);
            }
          );
      }
  
      if (modal) {
        console.log(this.receiverUsername);
        console.log(this.transferAmount);
        modal.style.display = "none";
      }
    });
  }


  submitTransferForm(): void {
    // Save the values of receiverUsername and transferAmount
    console.log('Receiver Username:', this.receiverUsername);
    console.log('Transfer Amount:', this.transferAmount);

    // Perform further actions or API calls if needed
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




























  // transferMoney(senderId: number, receiverUsername: string, amount: number) {
  //   const url = `/home/transfer/${senderId}/${receiverUsername}/${amount}`;
  //   const body = {}; // You can pass additional data in the request body if needed

  //   this.http.post(url, body).subscribe(
  //     (response) => {
  //       console.log('Money transfer successful:', response);
  //       // Handle the success response
  //     },
  //     (error) => {
  //       console.error('Error transferring money:', error);
  //       // Handle the error response
  //     }
  //   );
  // }







