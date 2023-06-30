import { Component, OnInit } from '@angular/core';
import { User } from 'src/entities/user';
import { UserService } from '../user.service';
import { FundTransfer } from 'src/entities/fund_transfer';
import { Observable, forkJoin } from 'rxjs';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public user: User | undefined;
  public senderFundTransfers: FundTransfer[] = [];
  public receiverFundTransfers: FundTransfer[] = [];
  public senderUsers: User[] = [];
  public receiverUsers: User[] = [];

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

  getSenderFundTransfers(userId: number): void {
    this.userService.getSenderFundTransfers(userId).subscribe(
      (fundTransfers: FundTransfer[]) => {
        this.senderFundTransfers = fundTransfers;
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
      },
      (error) => {
        console.log('Error retrieving receiver fund transfers:', error);
      }
    );
  }

  getSenderUsers(): void {
    const senderUserIds = this.senderFundTransfers.map(fundTransfer => fundTransfer.user_sender.id);
    this.retrieveUsers(senderUserIds).subscribe(
      (users: User[]) => {
        this.senderUsers = users;
      },
      (error) => {
        console.log('Error retrieving sender users:', error);
      }
    );
  }

  getReceiverUsers(): void {
    const receiverUserIds = this.receiverFundTransfers.map(fundTransfer => fundTransfer.user_receiver.id);
    this.retrieveUsers(receiverUserIds).subscribe(
      (users: User[]) => {
        this.receiverUsers = users;
      },
      (error) => {
        console.log('Error retrieving receiver users:', error);
      }
    );
  }

  retrieveUsers(userIds: number[]): Observable<User[]> {
    const requests: Observable<User>[] = userIds.map(userId =>
      this.userService.getUserById(userId)
    );

    return forkJoin(requests);
  }

}




// import { Component, OnInit } from '@angular/core';
// import { User } from 'src/entities/user';
// import { UserService } from '../user.service';

// @Component({
//   selector: 'app-user-profile',
//   templateUrl: './user-profile.component.html',
//   styleUrls: ['./user-profile.component.css']
// })
// export class UserProfileComponent implements OnInit {

//   public user: User | undefined;

//   constructor(private userService: UserService) { }

//   ngOnInit(): void {
//     this.userService.getCurrentUser().subscribe((user: User | undefined) => {
//       this.user = user;
//     });
//   }

// }
