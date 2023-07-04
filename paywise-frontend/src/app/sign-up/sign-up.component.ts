import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from 'src/entities/user';

// For view Child purposes
import { ViewChild } from '@angular/core';
import { SignUpBankInfoComponent } from '../sign-up-bank-info/sign-up-bank-info.component';
import { CardInfoComponent } from '../card-info/card-info.component';
import { SignUpSharedServiceComponent } from '../sign-up-shared-service/sign-up-shared-service.component';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  public newUser: User | undefined;

  // ViewChild references
  // @ViewChild(SignUpBankInfoComponent) signUpBankComponent: SignUpBankInfoComponent | undefined;
  // @ViewChild(CardInfoComponent) cardInfoComponent: CardInfoComponent | undefined;


  constructor(private userService: UserService, public userSharedService: SignUpSharedServiceComponent) { }

  ngOnInit(): void {
    
  }

  // const signUpBankComponentData = this.signUpBankComponent.getData();


}
