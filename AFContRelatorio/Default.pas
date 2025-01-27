
unit Default;

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
    procedure DropDownList4_SelectedIndexChanged(sender: TObject; e: System.EventArgs);
    procedure DDLMesComeco_SelectedIndexChanged(sender: TObject; e: System.EventArgs);
    procedure DDLMesFim_SelectedIndexChanged(sender: TObject; e: System.EventArgs);
  {$ENDREGION}
  strict private
    procedure Page_Load(sender: System.Object; e: System.EventArgs);
  strict protected
    GridView1: System.Web.UI.WebControls.GridView;
    SqlConnection1: System.Data.SqlClient.SqlConnection;
    sqlSelectCommand1: System.Data.SqlClient.SqlCommand;
    sqlInsertCommand1: System.Data.SqlClient.SqlCommand;
    sqlUpdateCommand1: System.Data.SqlClient.SqlCommand;
    sqlDeleteCommand1: System.Data.SqlClient.SqlCommand;
    SqlDataAdapter1: System.Data.SqlClient.SqlDataAdapter;
    DataSet1: System.Data.DataSet;
    DataTableCofre: System.Data.DataTable;
    Panel1: System.Web.UI.WebControls.Panel;
    RadioButton1: System.Web.UI.WebControls.RadioButton;
    RadioButton2: System.Web.UI.WebControls.RadioButton;
    DDLDiaComeco: System.Web.UI.WebControls.DropDownList;
    DDLMesComeco: System.Web.UI.WebControls.DropDownList;
    DDLAnoComeco: System.Web.UI.WebControls.DropDownList;
    DDLDiaFim: System.Web.UI.WebControls.DropDownList;
    DDLMesFim: System.Web.UI.WebControls.DropDownList;
    DDLAnoFim: System.Web.UI.WebControls.DropDownList;
    Button1: System.Web.UI.WebControls.Button;
  protected
    procedure OnInit(e: EventArgs); override;
    function Bissexto(ano:integer):boolean;
    procedure preencherDDL(ddl: boolean; fim: integer);
    procedure checkMesDDL(ddl:boolean);
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
var
  resources: System.ComponentModel.ComponentResourceManager;
begin
  resources := System.ComponentModel.ComponentResourceManager.Create(TypeOf(TDefault));
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
  Include(Self.DDLMesComeco.SelectedIndexChanged, Self.DDLMesComeco_SelectedIndexChanged);
  Include(Self.DDLDiaFim.SelectedIndexChanged, Self.DropDownList4_SelectedIndexChanged);
  Include(Self.DDLMesFim.SelectedIndexChanged, Self.DDLMesFim_SelectedIndexChanged);
  // 
  // SqlConnection1
  // 
  Self.SqlConnection1.ConnectionString := 'user id=sa;data source="(local)";' +
  'persist security info=True;initial catalog=AFCont;password=senha';
  Self.SqlConnection1.FireInfoMessageEventOnUserErrors := False;
  // 
  // sqlSelectCommand1
  // 
  Self.sqlSelectCommand1.CommandText := resources.GetString('sqlSelectComman' +
    'd1.CommandText');
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
var
  i : integer;
begin
  if not ispostback then
  begin
  DDLMesComeco.SelectedIndex := DateTime.get_Now.Month - 1;
  DDLAnoComeco.SelectedIndex := DateTime.get_Now.Year - 2000;
  DDLMesFim.SelectedIndex := DateTime.get_Now.Month;
  DDLMesFim.SelectedIndex := DateTime.get_Now.Month;
  if DateTime.get_Now.Month <> 1 then
    DDLAnoFim.SelectedIndex := DateTime.get_Now.Year - 1999
  else
    DDLAnoComeco.SelectedIndex := DateTime.get_Now.Year - 2000;
  end;
  checkMesDDL(true);
  checkMesDDL(false);  
  for I := 2000 to 2050 do
  begin
    DDLAnoComeco.Items.Add(i.ToString);
    DDLAnoFim.Items.Add(i.ToString);
  end;
  // TODO: Put user code to initialize the page here
  SqlDataAdapter1.Fill(DataSet1,'Cofre');
  GridView1.DataSource := DataTableCofre.DefaultView;
  databind;
end;

procedure TDefault.preencherDDL(ddl: boolean; fim: integer);
var
  i: integer;
begin
  if ddl = true then
    begin
      for i := 1 to fim do
       DDLDiaComeco.Items.Add(i.tostring);
    end
  else
    begin
      for I := 1 to fim do
        DDLDiaFim.Items.Add(i.tostring);
    end;
end;


procedure TDefault.OnInit(e: EventArgs);
begin
  //
  // Required for Designer support
  //
  InitializeComponent;
  inherited OnInit(e);
end;

function TDefault.Bissexto(ano: integer): boolean;
begin
  if ano mod 400 = 0 then
    result := true
  else if (ano mod 4 = 0) and (ano mod 100 <> 0) then
    result := true
  else
    result := false;
end;

procedure TDefault.checkMesDDL(ddl:boolean);
begin
  if ddl = true then
    begin
      case Convert.toint32(DDLMesComeco.SelectedItem.Value) of
      1: preencherDDL(ddl,31);
      2: if Bissexto(Convert.ToInt32(DDLAnoComeco.SelectedItem.Value)) then
            preencherDDL(ddl,29)
         else preencherDDL(ddl,28);
      3: preencherDDL(ddl,31);
      4: preencherDDL(ddl,30);
      5: preencherDDL(ddl,31);
      6: preencherDDL(ddl,30);
      7: preencherDDL(ddl,31);
      8: preencherDDL(ddl,31);
      9: preencherDDL(ddl,30);
      10: preencherDDL(ddl,31);
      11: preencherDDL(ddl,30);
      12: preencherDDL(ddl,31);
      end;
    end
  else
     begin
      case Convert.toint32(DDLMesFim.SelectedItem.Value) of
      1: preencherDDL(ddl,31);
      2: if Bissexto(Convert.ToInt32(DDLAnoFim.SelectedItem.Value)) then
            preencherDDL(ddl,29)
         else preencherDDL(ddl,28);
      3: preencherDDL(ddl,31);
      4: preencherDDL(ddl,30);
      5: preencherDDL(ddl,31);
      6: preencherDDL(ddl,30);
      7: preencherDDL(ddl,31);
      8: preencherDDL(ddl,31);
      9: preencherDDL(ddl,30);
      10: preencherDDL(ddl,31);
      11: preencherDDL(ddl,30);
      12: preencherDDL(ddl,31);
     end;
  end;



end;

procedure TDefault.DDLMesFim_SelectedIndexChanged(sender: TObject; e: System.EventArgs);
begin
  DDLDiaFim.Items.Clear;
  checkMesDDL(false);
end;

procedure TDefault.DDLMesComeco_SelectedIndexChanged(sender: TObject; e: System.EventArgs);
begin
  DDLDiaComeco.Items.Clear;
  checkMesDDL(true);



end;

procedure TDefault.DropDownList4_SelectedIndexChanged(sender: TObject; e: System.EventArgs);
begin

end;

end.

