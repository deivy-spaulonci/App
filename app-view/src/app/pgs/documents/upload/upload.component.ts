import { Component } from '@angular/core';
import {FileUploadModule} from "primeng/fileupload";
import {MessageService, PrimeNGConfig, PrimeTemplate} from "primeng/api";
import {CardModule} from "primeng/card";
import {ToastModule} from "primeng/toast";
import {BadgeModule} from "primeng/badge";
import {ButtonModule} from "primeng/button";
import {ProgressBarModule} from "primeng/progressbar";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {PanelModule} from "primeng/panel";

@Component({
  selector: 'app-upload',
  standalone: true,
  imports: [
    FileUploadModule, PrimeTemplate, CardModule, ToastModule, BadgeModule, ButtonModule, BadgeModule,
    ProgressBarModule, HttpClientModule, CommonModule, PanelModule
  ],
  templateUrl: './upload.component.html',
  styleUrl: './upload.component.css',
  providers: [MessageService]
})
export class UploadComponent {
  files = [];
  totalSize : number = 0;
  totalSizePercent : number = 0;
  maxFileSize:number=1000000000000000000;

  constructor(private config: PrimeNGConfig, private messageService: MessageService) {}

  choose(event: any, callback:any) {
    callback();
  }

  onError(event:any){
    let erroMsg = event.error.error;
    this.messageService.add({ severity: 'error', summary: 'Erro', detail: `Erro no upload ${erroMsg}` , life: 3000 });
  }

  onRemoveTemplatingFile(event:any, file:any, removeFileCallback:any, index:any) {
    removeFileCallback(event, index);
    this.totalSize -= parseInt(this.formatSize(file.size));
    this.totalSizePercent = this.totalSize / 10;
  }

  onClearTemplatingUpload(clear:any) {
    this.totalSize = 0;
    this.totalSizePercent = 0;
    clear();
  }

  onTemplatedUpload() {
    this.messageService.add({ severity: 'info', summary: 'Success', detail: 'Arquivos transferidos', life: 3000 });
  }

  onSelectedFiles(event:any) {
    this.files = event.currentFiles;
    this.files.forEach((file:any) => {
      this.totalSize += parseInt(this.formatSize(file.size));
    });
    this.totalSizePercent = this.totalSize / 10;
  }

  uploadEvent(callback: () => void) {
    callback();
  }

  formatSize(bytes: number) {
    const k = 1024;
    const dm = 3;
    const sizes = this.config.translation.fileSizeTypes;
    if (bytes === 0) {
      return `0 ${sizes ? sizes : [0]}`;
    }

    const i = Math.floor(Math.log(bytes) / Math.log(k));
    const formattedSize = parseFloat((bytes / Math.pow(k, i)).toFixed(dm));

    return `${formattedSize} ${sizes ? sizes : [i]}`;
  }

}
