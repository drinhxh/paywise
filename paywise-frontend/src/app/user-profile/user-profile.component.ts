import { Component, OnInit } from '@angular/core';
import { User } from 'src/entities/user';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public user: User | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
