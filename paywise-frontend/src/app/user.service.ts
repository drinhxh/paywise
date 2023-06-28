import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { User } from 'src/entities/user';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = environment.apiBaseUrl ;

  constructor(private http: HttpClient) { }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiServerUrl}/home/users`);
  }

  public addUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiServerUrl}/home/add`, user);
  }

  public updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiServerUrl}/home/update`, user);
  }

  public deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/home/delete/${userId}`);
  }

  public getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.apiServerUrl}/home/users/user/${userId}`);
  }

  public getUserByUsername(username: string): Observable<User>{
    return this.http.get<User>(`${this.apiServerUrl}/home/users/find/${username}`)
  }

  // PASSWORD MATCHER BCRYPT AND {noop}
  public login(username: string, password: string): Observable<User> {
    const user = { username, password };
    return this.http.post<User>(`${this.apiServerUrl}/home/login`, user);
  }

}
