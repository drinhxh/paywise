import { Component, Injectable, OnInit } from '@angular/core';
import { User } from 'src/entities/user';
import { UserService } from '../user.service';
import { HttpErrorResponse } from '@angular/common/http';

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
    // id: undefined, // Provide a default value for 'id'
    firstName: '',
    lastName: '',
    username: '',
    password: '',
    bankAccount: {
      // id: undefined, // Provide a default value for 'id'
      accountNumber: '',
      bankName: '',
      balance: 0 // Provide a default value for 'balance'
    },
    card: {
      // id: undefined, // Provide a default value for 'id'
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

  public saveUser(): void {
    console.log("saving user");
    this.userService.addUser(this.newUser).subscribe(
      (response: User) => {
        console.log("CREATED NEW USER");
        console.log(this.newUser);
        
        // Handle the response from the addUser() method
        // For example, display a success message or navigate to another page
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
