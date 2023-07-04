import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UsersComponent } from './users/users.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { CardInfoComponent } from './card-info/card-info.component';
import { LoginComponent } from './login/login.component';
import { MovingBackroundComponent } from './moving-backround/moving-backround.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignUpBankInfoComponent } from './sign-up-bank-info/sign-up-bank-info.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'users', component:UsersComponent },
  { path: 'not-found', component: NotFoundComponent },
  { path: 'card-info', component: CardInfoComponent },
  { path: 'login', component: LoginComponent },
  { path: 'moving-background', component: MovingBackroundComponent },
  { path: 'user-profile', component: UserProfileComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'sign-up-bank-info', component: SignUpBankInfoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
