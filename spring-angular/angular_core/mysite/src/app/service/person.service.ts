import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Person } from '../person';


@Injectable({
  providedIn: 'root'
})
export class PersonService {

  
  private apiUrl = 'http://localhost:8080';
  private personCreateUrl = this.apiUrl + '/create';
  private personListUrl = this.apiUrl + '/personList';
  private deleteUrl = this.apiUrl + '/person'


  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }
  getPersons(): Observable<Person[]>{
      return this.http.get<Person[]>(this.personListUrl )
        .pipe(
          tap(_ => this.log('fetched persons')),
          catchError(this.handleError<Person[]>('getPersons', []))
        );
  }

  createPerson(): Observable<Person>{
    return this.http.get<Person>(this.personCreateUrl)
      .pipe(
        tap(_ => this.log('create person')),
        catchError(this.handleError<Person>('createPerson'))
      );
  }

  deleteHero(id: number): Observable<Person> {
    const url = `${this.deleteUrl}/${id}`;

    return this.http.delete<Person>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted hero id=${id}`)),
      catchError(this.handleError<Person>('deleteHero'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    console.log(message);
  }
}
