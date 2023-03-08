import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user'
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ViewFeeToPayServiceService {

  constructor(private http: HttpClient) { }

  viewFeeToPay(user: User, semester: number): Observable<any> {
    const url = 'http://localhost:8080/viewFeeToPay'; // replace with your API endpoint
    return this.http.post(url, user, { params: { semester: semester } });
  }
}
