import { Component, OnInit } from '@angular/core';
import { User } from 'src/entities/user';
import { UserService } from './user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  // title = 'paywise-frontend';

  public users: User[] = [];
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }


  public getUsers(): void {
    this.userService.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
