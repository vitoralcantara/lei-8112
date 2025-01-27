
unit RelatorioCofre;

interface

uses
  System.Collections, System.ComponentModel,
  System.Data, System.Drawing, System.Web, System.Web.SessionState,
  System.Web.UI, System.Web.UI.WebControls, System.Web.UI.HtmlControls,
  System.Web.Security, System.Web.UI.WebControls.WebParts, System.Configuration, 
  System.Data.SqlClient;

type
  TDefault = class(System.Web.UI.Page)
  {$REGION 'Designer Managed Code'}
  strict private
    procedure InitializeComponent;
    //procedure DropDownList4_SelectedIndexChanged(sender: TObject; e: System.EventArgs);
    //procedure DDLMesComeco_SelectedIndexChanged(sender: TObject; e: System.EventArgs);
    //procedure DDLMesFim_SelectedIndexChanged(sender: TObject; e: System.EventArgs);
    //procedure Button2_Click(sender: TObject; e: System.EventArgs);
    //procedure RBMes_CheckedChanged(sender: TObject; e: System.EventArgs);
    //procedure RBPeriodo_CheckedChanged(sender: TObject; e: System.EventArgs);
    //procedure Button2_Click1(sender: TObject; e: System.EventArgs);
    procedure Button2_Click(sender: TObject; e: System.EventArgs);
    procedure ImageButton1_Click(sender: TObject; e: System.Web.UI.ImageClickEventArgs);
  {$ENDREGION}
  strict private
    procedure Page_Load(sender: System.Object; e: System.EventArgs);
  strict protected
    SqlConnection1: System.Data.SqlClient.SqlConnection;
    sqlSelectCommand1: System.Data.SqlClient.SqlCommand;
    sqlInsertCommand1: System.Data.SqlClient.SqlCommand;
    sqlUpdateCommand1: System.Data.SqlClient.SqlCommand;
    sqlDeleteCommand1: System.Data.SqlClient.SqlCommand;
    SqlDataAdapter1: System.Data.SqlClient.SqlDataAdapter;
    DataSet1: System.Data.DataSet;
    DataTableCofre: System.Data.DataTable;
    Panel1: System.Web.UI.WebControls.Panel;
    LabelAlerta: System.Web.UI.WebControls.&Label;
    RBMes: System.Web.UI.WebControls.RadioButton;
    RBPeriodo: System.Web.UI.WebControls.RadioButton;
    CalendarEntrada: System.Web.UI.WebControls.Calendar;
    CalendarSaida: System.Web.UI.WebControls.Calendar;
    GridView1: System.Web.UI.WebControls.GridView;
    Button2: System.Web.UI.WebControls.Button;
    Button1: System.Web.UI.WebControls.Button;
    HyperLink1: System.Web.UI.WebControls.HyperLink;
    HyperLink2: System.Web.UI.WebControls.HyperLink;
    RadioButton1: System.Web.UI.WebControls.RadioButton;
    RadioButton2: System.Web.UI.WebControls.RadioButton;
    RadioButton3: System.Web.UI.WebControls.RadioButton;
  protected
    procedure OnInit(e: EventArgs); override;
    //function Bissexto(ano:integer):boolean;
    //procedure checkMesDDL(ddl:boolean);
  private
    { Private Declarations }
  public
    { Public Declarations }

  end;

implementation

{$REGION 'Designer Managed Code'}
/// <summary>
/// Required method for Designer support -- do not modify
/// the contents of this method with the code editor.
/// </summary>
procedure TDefault.InitializeComponent;
type
  TArrayOfSystem_Data_DataTable = array of System.Data.DataTable;
begin
  Self.SqlConnection1 := System.Data.SqlClient.SqlConnection.Create;
  Self.sqlSelectCommand1 := System.Data.SqlClient.SqlCommand.Create;
  Self.sqlInsertCommand1 := System.Data.SqlClient.SqlCommand.Create;
  Self.sqlUpdateCommand1 := System.Data.SqlClient.SqlCommand.Create;
  Self.sqlDeleteCommand1 := System.Data.SqlClient.SqlCommand.Create;
  Self.SqlDataAdapter1 := System.Data.SqlClient.SqlDataAdapter.Create;
  Self.DataSet1 := System.Data.DataSet.Create;
  Self.DataTableCofre := System.Data.DataTable.Create;
  (System.ComponentModel.ISupportInitialize(Self.DataSet1)).BeginInit;
  (System.ComponentModel.ISupportInitialize(Self.DataTableCofre)).BeginInit;
  Include(Self.Button2.Click, Self.Button2_Click);
  // 
  // SqlConnection1
  // 
  Self.SqlConnection1.ConnectionString := 'user id=sa;data source="CESAR-9A7' +
  'E5DF0A\SQLEXPRESS";persist security info=True;initial catalog=AFCont;pass' +
  'word=senha';
  Self.SqlConnection1.FireInfoMessageEventOnUserErrors := False;
  // 
  // sqlSelectCommand1
  // 
  Self.sqlSelectCommand1.Connection := Self.SqlConnection1;
  // 
  // SqlDataAdapter1
  // 
  Self.SqlDataAdapter1.DeleteCommand := Self.sqlDeleteCommand1;
  Self.SqlDataAdapter1.InsertCommand := Self.sqlInsertCommand1;
  Self.SqlDataAdapter1.SelectCommand := Self.sqlSelectCommand1;
  Self.SqlDataAdapter1.UpdateCommand := Self.sqlUpdateCommand1;
  // 
  // DataSet1
  // 
  Self.DataSet1.DataSetName := 'NewDataSet';
  Self.DataSet1.Tables.AddRange(TArrayOfSystem_Data_DataTable.Create(Self.DataTableCofre));
  // 
  // DataTableCofre
  // 
  Self.DataTableCofre.TableName := 'Cofre';
  Include(Self.Load, Self.Page_Load);
  (System.ComponentModel.ISupportInitialize(Self.DataSet1)).EndInit;
  (System.ComponentModel.ISupportInitialize(Self.DataTableCofre)).EndInit;
end;
{$ENDREGION}

procedure TDefault.Page_Load(sender: System.Object; e: System.EventArgs);
  begin
  end;

procedure TDefault.OnInit(e: EventArgs);
begin
  //
  // Required for Designer support
  //
  InitializeComponent;
  inherited OnInit(e);
end;

procedure TDefault.ImageButton1_Click(sender: TObject; e: System.Web.UI.ImageClickEventArgs);
begin
end;

procedure TDefault.Button2_Click(sender: TObject; e: System.EventArgs);
const
  stringSearch = 'select Cofre.cod_cofre as Codigo, Usuario.nome as Funcionario,'
  +'Cofre.data_operacao as "Data de Opera��o", Cofre.entrada_saida as Tipo, '
  +'Cofre.descricao as Descri��o , Cofre.valor as Valor, Day(Caixa.data_hora_abertura) as '
  +'"Data Abertura Caixa" from Caixa, Usuario, Cofre where '
  +'Cofre.cod_usuario = Usuario.cod_usuario and Cofre.cod_caixa = Caixa.cod_caixa '
  +'and Cofre.data_operacao > '+ Chr(39) +' {0} '+chr(39)+' and Cofre.data_operacao < '+chr(39)+' {1} '+chr(39);
var
  tempEntrada : string;
  tempSaida : string;

begin
  if RBPeriodo.Checked = false then
    begin
      tempEntrada := '01/'+DateTime.get_Now.Month.ToString+'/'
      +DateTime.get_Now.year.ToString+' 00:00:00';
      tempSaida := DateTime.get_Now.ToString;
      LabelAlerta.text := 'RbMes Checked';
    end
  else
    begin
       tempEntrada := CalendarEntrada.SelectedDate.Date.ToString;
       tempSaida := CalendarSaida.SelectedDate.Date.ToString;
    end;
  if CalendarEntrada.SelectedDate > CalendarSaida.SelectedDate then
    LabelAlerta.Text := 'Opa! Foi selecionado inversamente um intervalo para o Calend�rio'
  else
  begin
    SqlConnection1.Open;
    sqlSelectCommand1.CommandText := System.String.Format(stringSearch,tempEntrada,tempSaida);
    sqlSelectCommand1.ExecuteNonQuery;
    SqlDataAdapter1.Fill(DataSet1,'Cofre');
    GridView1.DataSource := DataTableCofre.DefaultView;
    databind;
    SqlConnection1.Close;
    //LabelAlerta.Text := System.String.Format(stringSearch,tempEntrada,tempSaida);
  end;      
  

  
end;

end.
