import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { MatDividerModule } from '@angular/material/divider';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

// drag and drop
import { DndModule } from 'ng2-dnd';

// import material components
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatStepperModule } from '@angular/material/stepper';
import {
  MatSidenavModule,
  MatToolbarModule,
  MatCheckboxModule,
  MatProgressSpinnerModule
} from '@angular/material';
import { MatRadioModule } from '@angular/material/radio';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTabsModule } from '@angular/material/tabs';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule, MatDialogRef } from '@angular/material';
import { MatSelectModule } from '@angular/material/select';
import { MatListModule } from '@angular/material/list';
import { MatNativeDateModule } from '@angular/material';
import { MatChipsModule } from '@angular/material/chips';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatSortModule } from '@angular/material/sort';



// import own components
import { MytoolbarComponent } from './layout/mytoolbar/mytoolbar.component';
import { SidenavComponent } from './layout/sidenav/sidenav.component';
import { DataService } from '../dataservice';
import { RouterModule } from '@angular/router';


// import arrayFilteredGroups
import { filterPregPipe, MyFilterPipe, MyFilterPipe2, MyFilterName, MyFilterCategory, Filters, Filtros, filterPregs } from './mypipe';

// import services
import { HttpService } from './http.service';
import { FooterComponent } from './layout/footer/footer.component';
import { EstudianteService } from '../services/estudiante.service';
import { DocenteService } from '../services/docente.service';
import { AdminService } from '../services/admin.service';
import { DerivadasService } from '../services/derivadas.service';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    HttpClientModule,
    MatDividerModule,
    MatTabsModule,
    MatPaginatorModule,
    MatTableModule,
    MatCardModule,
    FormsModule,
    MatSnackBarModule,
    MatRadioModule,
    MatDialogModule,
    MatStepperModule,
    MatSelectModule,
    MatExpansionModule,
    MatListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTooltipModule,
    MatChipsModule,
    MatAutocompleteModule,
    MatPaginatorModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    DndModule,
    MatProgressBarModule,
    MatSortModule,
    RouterModule,
    MatProgressSpinnerModule
  ],
  declarations: [
    SidenavComponent, MyFilterPipe,
    MyFilterPipe2,
    MyFilterName,
    MyFilterCategory,
    MytoolbarComponent,
    FooterComponent,
    Filters,
    Filtros,
    filterPregs,
    filterPregPipe
  ],
  exports: [MytoolbarComponent, SidenavComponent,
    DndModule,
    FooterComponent,
    CommonModule,
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    HttpClientModule,
    MatDividerModule,
    MatTabsModule,
    MatPaginatorModule,
    MatTableModule,
    MatCardModule,
    FormsModule,
    MatSnackBarModule,
    MatRadioModule,
    MatDialogModule,
    MatStepperModule,
    MyFilterPipe,
    MyFilterPipe2,
    MyFilterName,
    MyFilterCategory,
    MatSelectModule,
    MatExpansionModule,
    MatListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTooltipModule,
    MatChipsModule,
    Filters,
    Filtros,
    MatAutocompleteModule,
    MatPaginatorModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    MatProgressBarModule,
    MatSortModule,
    filterPregs,
    RouterModule,
    MatProgressSpinnerModule,
    filterPregPipe
  ],
  schemas: [NO_ERRORS_SCHEMA],
  providers: [HttpService, DataService, EstudianteService, DocenteService, AdminService, DerivadasService],
})
export class ComunModule { }
