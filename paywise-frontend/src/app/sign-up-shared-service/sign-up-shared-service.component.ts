import { Component, Injectable, OnInit } from '@angular/core';
import { User } from 'src/entities/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-sign-up-shared-service',
  templateUrl: './sign-up-shared-service.component.html',
  styleUrls: ['./sign-up-shared-service.component.css']
})

@Injectable({
  providedIn: 'root'
})

export class SignUpSharedServiceComponent implements OnInit {

  public newUser: User = {
    id: 0, // Provide a default value for 'id'
    firstName: '',
    lastName: '',
    username: '',
    password: '',
    bankAccount: {
      id: 0, // Provide a default value for 'id'
      accountNumber: '',
      bankName: '',
      balance: 0 // Provide a default value for 'balance'
    },
    card: {
      id: 0, // Provide a default value for 'id'
      cardNumber: '',
      cardHolder: '',
      expiration: '',
      securityCode: ''
    },
    senderFundTransfers: [],
    receiverFundTransfers: [],
    roles: []
  };

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  saveUser(): void {
    this.userService.addUser(this.newUser).subscribe(
      (response: User) => {
        // Handle the response from the addUser() method
        // For example, display a success message or navigate to another page
      },
      (error: any) => {
        // Handle the error if addUser() fails
      }
    );
  }

}
