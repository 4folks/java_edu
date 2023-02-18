import { Component } from '@angular/core';
import { Person } from '../person';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent {

  persons: Person[] = [];

  constructor(private personService: PersonService){}

  ngOnInit(): void {
    this.getPersons();
  }

  getPersons(): void {
    this.personService.getPersons()
    .subscribe(persons => this.persons = persons);
  }

  createPerson(): void {
    this.personService.createPerson()
      .subscribe(p => p);
    console.log("createPerson complete");
  }

  delete(person: Person): void {
    this.persons = this.persons.filter(p => p !== person);
    this.personService.deleteHero(person.id).subscribe();
  }

}
