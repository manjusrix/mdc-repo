package com.example.requestorspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MicroserviceResponse {
  ArrayList<String> path;
}
