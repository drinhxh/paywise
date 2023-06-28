import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/entities/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  // onSubmit() {
  //   if (this.loginForm.valid) {
  //     const username = this.loginForm.value.username;
  //     const password = this.loginForm.value.password;
  
  //     this.userService.login(username, password).subscribe(
  //       (response) => {
  //         this.router.navigate(['/user-profile']);
  //       },
  //       (error: HttpErrorResponse) => {
  //         alert(error.message);
  //       }
  //     );
  //   }
  // }

  onSubmit() {
    if (this.loginForm.valid) {
      const username = this.loginForm.value.username;
      const password = this.loginForm.value.password;
      console.log(username);
      console.log(password);
      // Check if the username exists and verify the password
      this.userService.getUserByUsername(username).subscribe(
        (user: User) => {
          if (user) {
            console.log("response passed");
            if (user.password === password) {
              // Username and password are correct
              this.router.navigate(['/user-profile']);
            } else {
              // Password is incorrect
              alert('Invalid password');
            }
          } else {
            // Username does not exist
            alert('Invalid username');
          }
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }  
}





/*


WITHOUT BCRYPT AND {NOOP} COMPARISON



import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/entities/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const username = this.loginForm.value.username;
      const password = this.loginForm.value.password;
      console.log(username);
      console.log(password);
      // Check if the username exists and verify the password
      this.userService.getUserByUsername(username).subscribe(
        (user: User) => {
          if (user) {
            console.log("response passed");
            if (user.password === password) {
              // Username and password are correct
              this.router.navigate(['/user-profile']);
            } else {
              // Password is incorrect
              alert('Invalid password');
            }
          } else {
            // Username does not exist
            alert('Invalid username');
          }
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }
}

*/


