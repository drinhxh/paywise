import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Import the FormsModule and ReactiveFormsModule

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './user.service';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { UsersComponent } from './users/users.component';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { CardInfoComponent } from './card-info/card-info.component';
import { LoginComponent } from './login/login.component';
import { MovingBackroundComponent } from './moving-backround/moving-backround.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignUpBankInfoComponent } from './sign-up-bank-info/sign-up-bank-info.component';
import { SignUpSharedServiceComponent } from './sign-up-shared-service/sign-up-shared-service.component';
import { TestComponent } from './test/test.component';
import { TmpComponent } from './tmp/tmp.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UsersComponent,
    MenuComponent,
    NotFoundComponent,
    CardInfoComponent,
    LoginComponent,
    MovingBackroundComponent,
    UserProfileComponent,
    SignUpComponent,
    SignUpBankInfoComponent,
    SignUpSharedServiceComponent,
    TestComponent,
    TmpComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule, // Add the FormsModule here
    ReactiveFormsModule,
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
