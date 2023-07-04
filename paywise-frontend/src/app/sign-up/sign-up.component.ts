import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from 'src/entities/user';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  public newUser: User | undefined;


  constructor(private userService: UserService) { }

  ngOnInit(): void {

  }

  // submitForm(firstName: string, lastName: string, username: string, password: string) {
  //   const user = {
  //     firstName: firstName,
  //     lastName: lastName,
  //     username: username,
  //     password: password,
  //     bankAccount: null,
  //     card: null
  //   };

  //   this.userService.addUser(user).subscribe(
  //     (response) => {
  //       console.log('User added successfully:', response);
  //       // Optionally perform any additional actions upon successful user addition
  //     },
  //     (error) => {
  //       console.error('Error adding user:', error);
  //       // Handle error, display an error message, etc.
  //     }
  //   );
  // }


}
