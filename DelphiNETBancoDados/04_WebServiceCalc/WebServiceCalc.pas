unit WebServiceCalc;

interface

uses
  System.Collections, System.ComponentModel,
  System.Data, System.Diagnostics, System.Web,
  System.Web.Services;

type
  /// <summary>
  /// Summary description for WebService1.
  /// </summary>
  TWebService1 = class(System.Web.Services.WebService)
  {$REGION 'Designer Managed Code'}
  strict private
    /// <summary>
    /// Required designer variable.
    /// </summary>
    components: IContainer;
    /// <summary>
    /// Required method for Designer support - do not modify
    /// the contents of this method with the code editor.
    /// </summary>
    procedure InitializeComponent;
  {$ENDREGION}
  strict protected
    /// <summary>
    /// Clean up any resources being used.
    /// </summary>
    procedure Dispose(disposing: boolean); override;
  private
    { Private Declarations }
  public
    constructor Create;

    // Sample Web Service Method
    [WebMethod]
    function HelloWorld: string;

    [WebMethod]
    function Soma(a, b : integer): integer;

    [WebMethod]
    function Subtracao(a, b : integer): integer;

    [WebMethod]
    function Produto(a, b : integer): integer;

    [WebMethod]
    function Divisao(a, b : integer): integer;

  end;

implementation

{$REGION 'Designer Managed Code'}
/// <summary>
/// Required method for Designer support - do not modify
/// the contents of this method with the code editor.
/// </summary>
procedure TWebService1.InitializeComponent;
begin

end;

{$ENDREGION}

constructor TWebService1.Create;
begin
  inherited;
  //
  // Required for Designer support
  //
  InitializeComponent;
  //
  // TODO: Add any constructor code after InitializeComponent call
  //
end;

/// <summary>
/// Clean up any resources being used.
/// </summary>
procedure TWebService1.Dispose(disposing: boolean);
begin
  if disposing and (components <> nil) then
    components.Dispose;
  inherited Dispose(disposing);
end;

// Sample Web Service Method
// The following method is provided to allow for testing a new web service.

// Fun��es Publicadas pelo WebService

function TWebService1.HelloWorld: string;
begin
  Result := 'Alo Mundo';
end;

function TWebService1.Divisao(a, b: integer): integer;
begin
  Result := a div b;
end;

function TWebService1.Produto(a, b: integer): integer;
begin
  Result := a * b;
end;

function TWebService1.Soma(a, b: integer): integer;
begin
  Result := a + b;
end;

function TWebService1.Subtracao(a, b: integer): integer;
begin
  Result := a - b;
end;


end.

