import { Component, OnInit } from '@angular/core';
import { SignUpSharedServiceComponent } from '../sign-up-shared-service/sign-up-shared-service.component';
import { UserService } from '../user.service';
import { User } from 'src/entities/user';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-card-info',
  templateUrl: './card-info.component.html',
  styleUrls: ['./card-info.component.css', './card-info.component.scss']
})
export class CardInfoComponent implements OnInit {

  public cardNumber1: string = '';
  public cardNumber2: string = '';
  public cardNumber3: string = '';
  public cardNumber4: string = '';

  public cardExperationMonth: string = '';
  public cardExperationYear:  string = '';

  constructor(private userService: UserService, public userSharedService: SignUpSharedServiceComponent) { }

  ngOnInit(): void {
  }

  setCardInfo(){
    const cardNum = this.cardNumber1 + " " + this.cardNumber2 + " " + this.cardNumber3 + " " + this.cardNumber4;
    this.userSharedService.newUser.card.cardNumber = cardNum;
    const experation = this.cardExperationMonth + "/" + this.cardExperationYear;
    this.userSharedService.newUser.card.expiration = experation;

    this.userSharedService.newUser.id = undefined;
  }

  saveUser(): void {
    this.setCardInfo();
    this.userService.addUser(this.userSharedService.newUser).subscribe(
      (response: User) => {
        // console.log(this.userSharedService.newUser);
        alert(this.userSharedService.newUser.firstName);
      },
      (error: any) => {
      }
    );
  }

}
