import {Component, OnInit} from '@angular/core';
import {FilesComponent} from "../files/files.component";
import {CardModule} from "primeng/card";
import {PanelModule} from "primeng/panel";
import {DefaultService} from "../../../service/default-service";
import {ConfirmationService, MessageService} from "primeng/api";
import {TypeDocument} from "../../../model/type-document";
import {DropdownModule} from "primeng/dropdown";
import {InputTextareaModule} from "primeng/inputtextarea";
import {Button} from "primeng/button";

@Component({
  selector: 'app-document',
  standalone: true,
  imports: [
    FilesComponent,
    CardModule,
    PanelModule,
    DropdownModule,
    InputTextareaModule,
    Button
  ],
  templateUrl: './document.component.html',
  styleUrl: './document.component.css'
})
export class DocumentComponent implements OnInit{
  documents:Document[]=[];
  types:TypeDocument[]=[];

  constructor(private defaultService: DefaultService) {

  }

  ngOnInit(): void {
    this.loadTypes();
  }

  loadTypes(){
    this.defaultService.get('type_document').subscribe({
      next: result => {
        this.types = result;
      },error: error =>{
        console.log("error ...")
        console.log(JSON.stringify(error.error))
      },complete:()=>{
        console.log("completo")
        console.log(this.types)
        // this.blockedDocument = false;
        // this.totalSizeFiles = this.files.reduce((subtotal, f2) => subtotal + Number(f2.size), 0);
      }
    });
  }

  loadDocs(){
    this.defaultService.get('documents').subscribe({
      next: result => {
        this.documents = result;
      },error: error =>{
        console.log("error ...")
        console.log(JSON.stringify(error.error))
      },complete:()=>{
        console.log("completo")
        console.log(this.documents)
        // this.blockedDocument = false;
        // this.totalSizeFiles = this.files.reduce((subtotal, f2) => subtotal + Number(f2.size), 0);
      }
    });
  }
}
