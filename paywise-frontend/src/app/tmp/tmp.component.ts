import { Component, OnInit } from '@angular/core';
import { SignUpSharedServiceComponent } from '../sign-up-shared-service/sign-up-shared-service.component';

@Component({
  selector: 'app-tmp',
  templateUrl: './tmp.component.html',
  styleUrls: ['./tmp.component.css']
})
export class TmpComponent implements OnInit {

  constructor(public userSharedService: SignUpSharedServiceComponent) { }

  ngOnInit(): void {
  }

}
