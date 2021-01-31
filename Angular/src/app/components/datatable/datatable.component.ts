import { map } from 'rxjs/operators';
import { FlightDataElement } from '../../dataStructures/FlightDataElement';
import { TableApiService } from './../../services/tableApi.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import * as _ from 'lodash';

@Component({
  selector: 'app-datatable',
  templateUrl: './datatable.component.html',
  styleUrls: ['./datatable.component.css']
})
export class DatatableComponent implements OnInit {

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  public originalData;
  public dataSource;
  public tableColumns = [
    'flightNo',
    'flightDate',
    'legNumber',
    'origin',
    'destination',
    'releaseNumber',
    'releaseTime'
  ];
  public rowPerPage = [10, 20, 40, 80, 100];
  public timeFilters = [ 1,3, 6, 12, 24 ];
  public currentTimeFilter;
  public selectedTimeFilterValue;
  public isTableDataLoading:boolean = true;
  public isTableDataValid:boolean = false;
  public filterOrigin:string="";
  public filterDestination:string="";

  constructor(private _tableService:TableApiService) { }

  ngOnInit() {
    this.selectedTimeFilterValue = this.timeFilters[0];
    this.currentTimeFilter = this.timeFilters[0];
    this.initTableData(this.selectedTimeFilterValue);

  }

  ngAfterViewInit(): void {
  }

  initTableData(hr = this.currentTimeFilter){
    const promise = this._tableService.getFlightTableData(hr).toPromise();
    promise.then(
      (data) => {
        //to make the reset filter work properly
        //if for 1 hr should be our initial data
        if(hr==1){
        this.originalData = data;
        }
        this.isTableDataLoading = false;
        // this.resetTableData(this.originalData);
        this.resetTableData(data)
        this.isTableDataValid = true;
      },
      (error) => {
        this.isTableDataLoading = false;
        console.log(error);
        this.isTableDataValid = false;
      }
    );
  } // initTableDataEnd

  resetTableData(data:FlightDataElement[]){
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  } // resetTableDataEnd

  //Some modifications!!
  applyFilter() {
    let fOrigin = this.filterOrigin.toLocaleLowerCase().trim();
    let fDestination = this.filterDestination.toLocaleLowerCase().trim();
     let data = this.originalData.filter( (element:FlightDataElement)=>{
      return (element.origin.toLowerCase().startsWith(fOrigin) &&
        element.destination.toLowerCase().startsWith(fDestination))
    })
    // var data = _.filter(this.originalData ,
    //   function(obj) {
    //     return _.startsWith(obj["origin"], fOrigin) && _.startsWith(obj["destination"], fDestination);
    //   }
    // );

    this.resetTableData(data);
  } // applyFilterEnd

  applyTimeFilter() {
    console.log('getting from server ',this.currentTimeFilter, this.selectedTimeFilterValue);
      this.initTableData(this.selectedTimeFilterValue);
      this.currentTimeFilter = this.selectedTimeFilterValue;

    //When again the 1 hr is selected filter is not working
    // if(this.currentTimeFilter<this.selectedTimeFilterValue){
    //   console.log('getting from server ',this.currentTimeFilter, this.selectedTimeFilterValue);
    //   this.initTableData(this.selectedTimeFilterValue);
    //   this.currentTimeFilter = this.selectedTimeFilterValue;
    // }
    // else{
    //   console.log('filtering to ',this.selectedTimeFilterValue);
    // }
  }

  resetFilter(){
    this.resetTableData(this.originalData);
    this.filterDestination = "";
    this.filterOrigin = "";
  }

}
