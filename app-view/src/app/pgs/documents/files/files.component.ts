import {Component, OnInit} from '@angular/core';
import {ListboxModule} from "primeng/listbox";
import {FormsModule} from "@angular/forms";
import {NgClass, NgIf, SlicePipe} from "@angular/common";
import {TableModule} from "primeng/table";
import {ToolbarModule} from "primeng/toolbar";
import {BlockUIModule} from "primeng/blockui";
import {InputTextModule} from "primeng/inputtext";
import {ConfirmationService, MenuItem, MessageService} from "primeng/api";
import {ToastModule} from "primeng/toast";
import {Button, ButtonDirective} from "primeng/button";
import {Ripple} from "primeng/ripple";
import {DialogModule} from "primeng/dialog";
import {CardModule} from "primeng/card";
import {DividerModule} from "primeng/divider";
import {ContextMenuModule} from "primeng/contextmenu";
import {BreadcrumbModule} from "primeng/breadcrumb";
import {MenuModule} from "primeng/menu";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {FileUploadModule} from "primeng/fileupload";
import {InputGroupModule} from "primeng/inputgroup";
import {DefaultService} from "service/default-service";
import {PanelModule} from "primeng/panel";
import {DropdownModule} from "primeng/dropdown";
import {InputTextareaModule} from "primeng/inputtextarea";
import {TypeDocument} from "model/type-document";
import {Document} from "../../../model/document";


interface File {
  name: string,
  size: string
}
class RenameFile{
  oldName: string | undefined;
  newName: string | undefined
}

@Component({
  selector: 'app-files',
  standalone: true,
  imports: [BreadcrumbModule, ListboxModule, FormsModule, NgIf, TableModule, ToolbarModule, NgClass, BlockUIModule,
    InputTextModule, ToastModule, ButtonDirective, Ripple, DialogModule, Button, CardModule, DividerModule,
    ContextMenuModule, MenuModule, ConfirmDialogModule, FileUploadModule, InputGroupModule, SlicePipe, PanelModule, DropdownModule, InputTextareaModule],
  templateUrl: './files.component.html',
  styleUrl: './files.component.css',
  providers:[MessageService, ConfirmationService]
})
export class FilesComponent implements OnInit{
  iframe =  document.getElementById('ifr') as HTMLIFrameElement;
  files!:File[];
  selectedFile!:File;
  iframeData!:String;
  blockedDocument: boolean = false;
  totalSizeFiles:number=0;
  showEditNameDialog:boolean=false;
  newName:string="";
  items!: MenuItem[];
  types:TypeDocument[]=[];
  document!:Document;


  constructor(private defaultService: DefaultService,
              private confirmationService: ConfirmationService,
              private messageService: MessageService) {
    this.loadFiles();
  }

  ngOnInit(): void {
    this.iframe = document.getElementById('ifr') as HTMLIFrameElement;
    this.document = new Document();
    this.document.typeDocument = new TypeDocument();
    this.items = [
      { label: 'Editar', icon: 'pi pi-fw pi-pencil', command: () => this.showRenameFile()},
      { label: 'Excluir', icon: 'pi pi-fw pi-times', command: () => this.deleteFile() }
    ];

    this.loadTypes();
  }

  addFileToDoc(){

    this.document.fileName = this.selectedFile.name
    alert(JSON.stringify(this.document));
  }

  loadTypes(){
    this.defaultService.get('type_document').subscribe({
      next: result => {
        this.types = result;
      },error: error =>{
        console.log("error ...")
        console.log(JSON.stringify(error.error))
      },complete:()=>{
        this.blockedDocument = false;
      }
    });
  }

  loadFiles(){
    this.blockedDocument = true;
    this.defaultService.get('documents/files').subscribe({
      next: result => {
        this.files = result;
      },error: error =>{
        console.log("error ...")
        console.log(JSON.stringify(error.error))
      },complete:()=>{
        this.blockedDocument = false;
        this.totalSizeFiles = this.files.reduce((subtotal, f2) => subtotal + Number(f2.size), 0);
      }
    });
  }

  showRenameFile(){
    this.newName = this.selectedFile.name;
    this.showEditNameDialog = true
  }

  deleteFile(){
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir esse arquivo?',
      header: 'Confirmação',
      icon: 'pi pi-exclamation-triangle',
      acceptIcon:"none",
      rejectIcon:"none",
      rejectButtonStyleClass:"p-button-text",
      accept: () => {
        this.defaultService.get('documents/deleteFile/'+this.selectedFile.name).subscribe({
          error: error => {
            this.messageService.add({severity: 'error', summary: 'Erro', detail: "Erro ao apagar aqruivo!"});
          },
          complete:()=>{
            this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Arquivo exclído com sucesso!' });
            this.loadFiles();
          }
        });
      },
      reject: () => {}
    });
  }

  renameFile(){
    if(this.checkExtension(this.newName)){
      let renameFile:RenameFile= new RenameFile();
      renameFile.newName = this.newName;
      renameFile.oldName = this.selectedFile.name;
      this.defaultService.save(renameFile, 'documents/renameFile').subscribe( {
        error: error => {
          this.messageService.add({severity: 'error', summary: 'Erro', detail: "Nome do aqruivo inválido!"});
        },
        complete:()=>{
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Arquivo renomeado com sucesso' });
          this.loadFiles();
          this.showEditNameDialog = false;
        }
      });

    }else
      this.messageService.add({severity: 'error', summary: 'Erro', detail: "Nome do aqruivo inválido!"});
  }

  checkWitheSpaces(){
    this.newName = this.newName.replaceAll(" ", "_");
    this.newName = this.newName.replaceAll("-", "");
    this.newName = this.newName.replaceAll("__", "_");
    this.newName = this.newName.normalize('NFD').replace(/[\u0300-\u036f]/g, "");
  }

  showFile(event: any) {
    this.blockedDocument = true;
    let name = this.selectedFile.name;
    let url = 'documents/fileByName/'+name;

    this.defaultService.getTextHtml(url).subscribe( {
      next: resultado => {

        let extension = name.split('.').pop();
        if(extension && ['pdf', 'jpg', 'gif', 'jpeg'].indexOf(extension)!==-1){
          let app = (extension==='pdf'?'application':'image');
          this.iframeData = `data:${app}/${extension};base64,`;

          this.iframe.src = this.iframeData + resultado;
        }
      },
      error: error => {
        console.log("error ...")
        console.log(JSON.stringify(error.error))
      },
      complete:()=>{
        this.blockedDocument = false;
      }
    });
  }

  checkExtension(name:string):boolean{
    if(name.length>4){
      let ext = name.substring(name.length-3, name.length)
      if(['pdf', 'jpg', 'gif'].indexOf(ext)!==-1){
        return true;
      }else{
        ext = name.substring(name.length-4, name.length);
        return ext === 'jpeg';
      }
    }
    return false;
  }

}
