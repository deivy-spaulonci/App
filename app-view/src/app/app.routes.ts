import { Routes } from '@angular/router';
import {HomeComponent} from "./pgs/home/home.component";
import {FilesComponent} from "./pgs/documents/files/files.component";
import {UploadComponent} from "./pgs/documents/upload/upload.component";
import {DocumentComponent} from "./pgs/documents/document/document.component";

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'documents', component: DocumentComponent },
  { path: 'files', component: FilesComponent },
  { path: 'upload', component: UploadComponent },
  { path: '',   redirectTo: '/home', pathMatch: 'full' }
];
