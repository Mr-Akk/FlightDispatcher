import { FlightDataElement } from './../dataStructures/FlightDataElement';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TableApiService {

  private _urlFlightTableData:string = "/assets/data/table.json";

  constructor(private http: HttpClient) { }

  getFlightTableData(hr){
    console.log(hr);

    console.log(this._urlFlightTableData+'/'+hr);

    return this.http.get<FlightDataElement[]>(`http://localhost:8080/table/getData/${hr}`)

    // return this.http.get<FlightDataElement[]>(this._urlFlightTableData);

  }
}
