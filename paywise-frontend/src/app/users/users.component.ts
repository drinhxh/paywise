import { Component, OnInit } from '@angular/core';
import { User } from 'src/entities/user';
import { UserService } from '../user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users: User[] = [];
  public userByUsername: User | undefined;
  public userByUserId: User | undefined;

  // DUMMY
  public dummyUser: User | undefined;
  public id: number = 3;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
    this.getUserById(this.id);
  }

  public getUsers(): void {
    
    this.userService.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getUserByUsername(username: string): void {
    this.userService.getUserByUsername(username).subscribe(
      (response: User) => {
        this.userByUsername = response;
        console.log(response);

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getUserById(userId: number): void {
    this.userService.getUserById(userId).subscribe(
      (response: User) => {
        this.userByUserId = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
