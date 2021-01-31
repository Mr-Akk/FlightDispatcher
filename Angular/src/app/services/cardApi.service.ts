import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CardApiService {

  // private _urlDomestic:string = "/assets/data/cardDomestic.json";
  private _urlDomestic:string = "http://localhost:8080/card/getDomesticRelease";
  // private _urlInternational:string = "/assets/data/cardInter.json";
    private _urlInternational:string = "http://localhost:8080/card/getInternationalRelease";

  // private _urlIterations:string = "/assets/data/cardIterations.json";
    private _urlIterations:string = "http://localhost:8080/card/getTotalIterations";


  constructor(private http: HttpClient) { }

  getCardDomestic(){
    return this.http.get(this._urlDomestic);
  }
  getCardInternational(){
    return this.http.get(this._urlInternational);
  }
  getCardIterations(){
    return this.http.get(this._urlIterations);
  }
}
