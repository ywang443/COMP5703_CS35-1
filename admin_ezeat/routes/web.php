<?php

use Illuminate\Support\Facades\Route;
use App\Utils\AESCipherUtils;
use App\Http\Controllers\PublicController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return redirect('/admin');
    // return view('welcome');
});

Route::get('/users', function () {
      //Code to Test Class
    $iv = 'fedcba9876543210'; #Same as in JAVA
    $key = '0123456789abcdef'; #Same as in JAVA
    $data = "Hello World!!!!";

    // $aes = $encrypted = PHP_AES_Cipher::encrypt($key, $iv, $data);
    return $key;
});


Route::post('/users/login', function () {
    $res['code'] = 110;
    $res['msg'] = 100;
    return $res;
});

Route::post('/uploadimg', [PublicController::class,"uploadImg"]);