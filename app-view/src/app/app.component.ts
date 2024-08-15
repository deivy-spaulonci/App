import {Component, OnInit, ViewChild} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { MenubarModule } from 'primeng/menubar';
import {MenuItem} from "primeng/api";
import {TabViewModule} from "primeng/tabview";
import {TabMenuModule} from "primeng/tabmenu";
import {ToolbarModule} from "primeng/toolbar";
import {Sidebar, SidebarModule} from "primeng/sidebar";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ButtonModule, MenubarModule, TabViewModule, TabMenuModule, ToolbarModule, SidebarModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'app-view';
  itemsDoc: MenuItem[] | undefined;
  sidebarVisible:boolean = false;

  @ViewChild('sidebarRef') sidebarRef!: Sidebar;

  ngOnInit() {
    this.itemsDoc = [
      {label: 'Arquivos',icon: 'pi pi-copy', routerLink: '/files'},
      {label: 'Upload',icon: 'pi pi-upload', routerLink: '/upload'},
      {label: 'Documentos',icon: 'pi pi-file-o', routerLink: '/documents'}
    ]
  }
}
