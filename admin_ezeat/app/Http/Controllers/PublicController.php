<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class PublicController extends NeoController
{
  /**
   * 上传图片
   * 因为是post,所以需要注意csrf令牌
   */
  public function uploadImg(Request $request){
    $paramObj = $request->all();
    $file = $request->file("file");//获取file类型name为image的文件
    $rules = [
        'image'=>'required|max:5000|'  //验证文件大小，最大为5M
    ];
    $validator = Validator::make($paramObj, $rules);
    // if($validator->fails()){
    if(false){
        return $validator->errors()->first();
    }
    else{
        $filesize =  $file->getSize();//获取文件字节
        $fileTypes = array('jpg','png','gif');//设置文件类型数组
        $fil = $file->getClientOriginalName();//获取图片文件路径
        $fileType = $file->getClientOriginalExtension();//获取文件类型
        $isType = in_array($fileType,$fileTypes); //校验文件类型是否合法
        $tmpName = $file->getFilename();//获取缓存在tmp的文件名
        //根据时间戳以及随机数以及临时文件名再通过md5生成随机名称
        $fileName= md5(time().mt_rand(1,1000000).$tmpName).".png";
        $data = $file->move("upload/images",$fileName);//移动文件
        $r = [];
        $r['url'] ='images/' . $fileName;
        return $this->Json(0, $r,'上传成功');
    }

  }
   //上传图片
    public function uploadImage1(Request $request)
    {
        $path = $request->file('file')->store('upload', 'public');
        return $path;
    }

    function upload_img(Request $request)
    {
      $file = $request->file('image');
    $url_path = 'uploads/cover';
    $rule = ['jpg', 'png', 'gif'];
    if ($file->isValid()) {
      $clientName = $file->getClientOriginalName();
      $tmpName = $file->getFileName();
      $realPath = $file->getRealPath();
      $entension = $file->getClientOriginalExtension();
      if (!in_array($entension, $rule)) {
      return '图片格式为jpg,png,gif';
      }
      $newName = md5(date("Y-m-d H:i:s") . $clientName) . "." . $entension;
      $path = $file->move($url_path, $newName);
      $namePath = $url_path . '/' . $newName;
      return $path;
    }
}


}