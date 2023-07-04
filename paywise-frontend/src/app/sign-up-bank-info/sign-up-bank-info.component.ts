import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { SignUpSharedServiceComponent } from '../sign-up-shared-service/sign-up-shared-service.component';

@Component({
  selector: 'app-sign-up-bank-info',
  templateUrl: './sign-up-bank-info.component.html',
  styleUrls: ['./sign-up-bank-info.component.css']
})
export class SignUpBankInfoComponent implements OnInit {

  constructor(private userService: UserService, public userSharedService: SignUpSharedServiceComponent) { }

  ngOnInit(): void {
  }


}
