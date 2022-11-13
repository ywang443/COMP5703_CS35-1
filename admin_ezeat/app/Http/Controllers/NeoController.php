<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class NeoController extends Controller
{
    /**
     * json
     * @param $code int 返回状态码
     * @param $data string 返回数据
     * @return string
     */
    public function Json($code,$data,$msg){
		$r['code'] = $code;
		$r['data'] = $data;
		$r['msg'] = $msg;
		return json_encode($r);
	}
}
