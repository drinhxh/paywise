import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from 'src/entities/user';

// For view Child purposes
import { ViewChild } from '@angular/core';
import { SignUpBankInfoComponent } from '../sign-up-bank-info/sign-up-bank-info.component';
import { CardInfoComponent } from '../card-info/card-info.component';
import { SignUpSharedServiceComponent } from '../sign-up-shared-service/sign-up-shared-service.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  signUpForm!: FormGroup;

  constructor(private userService: UserService, 
              public userSharedService: SignUpSharedServiceComponent,
              private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.signUpForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  // const signUpBankComponentData = this.signUpBankComponent.getData();


}
